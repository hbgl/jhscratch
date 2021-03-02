package dev.hbgl.jhscratch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("dev.hbgl.jhscratch");

        noClasses()
            .that()
                .resideInAnyPackage("dev.hbgl.jhscratch.service..")
            .or()
                .resideInAnyPackage("dev.hbgl.jhscratch.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..dev.hbgl.jhscratch.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
