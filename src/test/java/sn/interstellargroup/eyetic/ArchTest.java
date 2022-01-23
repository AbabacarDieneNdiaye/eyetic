package sn.interstellargroup.eyetic;

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
            .importPackages("sn.interstellargroup.eyetic");

        noClasses()
            .that()
                .resideInAnyPackage("sn.interstellargroup.eyetic.service..")
            .or()
                .resideInAnyPackage("sn.interstellargroup.eyetic.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..sn.interstellargroup.eyetic.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
