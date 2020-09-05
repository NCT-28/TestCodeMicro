package com.spring.rest.base.controller;

import com.spring.jpa.base.hibernate.MetaEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.HashSet;
import java.util.Set;

@Tag(name = "Metamodel", description = "Metamodel Database")
public abstract class BaseMainController {

    private final EntityManager em;

    public BaseMainController(EntityManager em) {
        this.em = em;
    }

    private MetaEntity setValueMetaEntity(EntityType<?> entityType) {
        MetaEntity metaEntity = new MetaEntity();
        metaEntity.setName(entityType.getName());
        metaEntity.setPersistenceType(entityType.getPersistenceType());
        metaEntity.setHasSingleIdAttribute(entityType.hasSingleIdAttribute());
        metaEntity.setHasVersionAttribute(entityType.hasVersionAttribute());
        metaEntity.setIdType(entityType.getIdType());
        //
        Set<MetaEntity.Attribute> attributes = new HashSet<>();
        for (var attributeOriginal : entityType.getAttributes()) {
            MetaEntity.Attribute attributeClone = new MetaEntity.Attribute();
            attributeClone.setMember(attributeOriginal.getJavaMember());
            attributeClone.setPersistentAttributeType(attributeOriginal.getPersistentAttributeType());
            attributeClone.setAssociation(attributeOriginal.isAssociation());
            attributeClone.setCollection(attributeOriginal.isCollection());
            attributes.add(attributeClone);
        }
        metaEntity.setAttributes(attributes);
        return metaEntity;
    }

    @Operation(summary = "Creates list of Metamodels with name entity", tags = {"Metamodel"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "successful operation"))
    @GetMapping(value = "/MetaData/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MetaEntity> getMetaDataByName(@PathVariable("name") String name) throws ClassNotFoundException {
        return ResponseEntity.ok(setValueMetaEntity(em.getMetamodel().entity(Class.forName(name))));
    }

    @Operation(summary = "Creates list of Metamodels", tags = {"Metamodel"})
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "successful operation"))
    @GetMapping(value = "/MetaData", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<MetaEntity>> getMetaData() {
        Metamodel metamodel = em.getMetamodel();
        Set<MetaEntity> list = new HashSet<>();
        var allEntityTypes = metamodel.getEntities();
        for (var entityType : allEntityTypes) {
            list.add(setValueMetaEntity(entityType));
        }
        return ResponseEntity.ok(list);
    }
}