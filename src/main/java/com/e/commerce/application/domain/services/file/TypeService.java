package com.e.commerce.application.domain.services.file;

import com.e.commerce.application.domain.entities.Type;
import com.e.commerce.application.domain.exceptions.custom.DuplicateValueException;
import com.e.commerce.application.domain.repositories.TypeRepository;
import com.e.commerce.application.domain.utils.constant.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Type findTypeById(String id) {
        Type existType = typeRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Not found this type: " + id)
        );

        log.info(Logger.findObjectSuccess("type"));
        return existType;
    }

    public Type findTypeByName(String name) {
        Optional<Type> type = typeRepository.findByTypeName(name);
        return type.orElseThrow(() -> new NullPointerException("Not found this type: " + name));
    }

    public boolean checkTypeByName(String name) {
        Optional<Type> type = typeRepository.findByTypeName(name);
        return type.isPresent();
    }

    public void createAndCheckTypeDuplicate() {
        List<Type> types = typeRepository.findAll();
        if (types.isEmpty()) {
            createDefaultType();
        } else {
            List<String> existingTypeNames = typeRepository.findAllReturnName(); // Use a custom method to retrieve type names
            Set<String> requiredTypes = Set.of("file", "image", "csv", "pdf"); // Use a Set for efficient lookups

            List<String> missingTypes = requiredTypes.stream()
                    .filter(t -> !existingTypeNames.contains(t))
                    .collect(Collectors.toList());

            if (!missingTypes.isEmpty()) {
                for (String missingType : missingTypes) {
                    createCustomType(missingType); // Create the missing type
                }
            } else {
                // Log or handle the case where all types already exist
                log.error(Logger.createObjectFail("type"));
                log.info("All required types (file, image, csv, pdf) already exist.");
                throw new DuplicateValueException("Type is existed");
            }
        }
    }

    private void createDefaultType() {
        Type typeAny = new Type("file");
        Type typeImage = new Type("image");
        Type typeCsv = new Type("csv");
        Type typePdf = new Type("pdf");

        Set<Type> types = new HashSet<>();
        types.add(typeAny);
        types.add(typeImage);
        types.add(typeCsv);
        types.add(typePdf);

        typeRepository.saveAll(types);
        log.info(Logger.createListObjectSuccess("default types"));
    }

    public void createCustomType(String name) {
        Type type = new Type(name);
        typeRepository.save(type);
        log.info(Logger.createObjectSuccess("type"));
    }

    public void deleteType(String id) {
        Type existType = findTypeById(id);
        typeRepository.delete(existType);
        log.info(Logger.deleteObjectSuccess("type"));
    }
}
