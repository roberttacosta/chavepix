package com.pix.utils

import com.pix.config.exceptions.UnprocessableEntity
import com.pix.model.enums.TipoChaveEnum
import spock.lang.Specification

class ValidatorTest extends Specification {

    def "validate valorChave with tipoChave CPF when valid"() {
        when:
        def isValid = Validator.isCpfValid(paramValue, TipoChaveEnum.CPF)

        then:
        isValid == result

        where:

        paramValue    | result
        "12659459690" | true
        "69499456040" | true
        "96371592076" | true
    }

    def "validate valorChave with tipoChave CPF when invalid"() {
        when:
        Validator.isCpfValid(paramValue, TipoChaveEnum.CPF)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue    | result
        "11111111111" | null
        "12659459691" | null
        "000"         | null
    }

    def "validate valorChave with tipoChave CNPJ when valid"() {
        when:
        def isValid = Validator.isCNPJValid(paramValue, TipoChaveEnum.CNPJ)

        then:
        isValid == result

        where:

        paramValue       | result
        "21037120000133" | true
        "47449555000189" | true
        "12854474000180" | true
    }

    def "validate valorChave with tipoChave CNPJ when invalid"() {
        when:
        Validator.isCNPJValid(paramValue, TipoChaveEnum.CNPJ)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue       | result
        "21037120000130" | null
        "11111111111111" | null
        "000"            | null
    }

    def "validate valorChave with tipoChave Celular when valid"() {
        when:
        def isValid = Validator.isValidCelular(paramValue, TipoChaveEnum.CELULAR)

        then:
        isValid == result

        where:

        paramValue        | result
        "+55341992345678" | true
        "+5534998767676"  | true
        "+5531996780910"  | true
    }

    def "validate valorChave with tipoChave Celular when invalid"() {
        when:
        Validator.isValidCelular(paramValue, TipoChaveEnum.CELULAR)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue      | result
        "5534991929292" | null
        "+34992324040"  | null
        "+553291234568" | null
    }

    def "validate valorChave with tipoChave Email when valid"() {
        when:
        def isValid = Validator.isValidEmail(paramValue, TipoChaveEnum.EMAIL)

        then:
        isValid == result

        where:

        paramValue            | result
        "jose@hotmail.com.br" | true
        "jose@hotmail.com"    | true
        "jo@gmail.br"         | true
    }

    def "validate valorChave with tipoChave Email when invalid"() {
        when:
        Validator.isValidEmail(paramValue, TipoChaveEnum.EMAIL)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue      | result
        "josegmailcom"  | null
        "jose@gmailcom" | null
        "josegmail.com" | null
    }

    def "validate valorChave with tipoChave Aleatoria when valid"() {
        when:
        def isValid = Validator.isValidChaveAleatoria(paramValue, TipoChaveEnum.ALEATORIO)

        then:
        isValid == result

        where:

        paramValue                             | result
        "AAAABBBBCCCCDDDDEEEEFFFFIIIIJJJJKKKK" | true
        "AAAACCCCDDDDEEEEFFFFIIIIJJJJKKKK"     | true
        "AAAACCCCDDDDEEEEFFFFIIIIJJJJKKKK0000" | true
    }

    def "validate valorChave with tipoChave Aleatorio when invalid"() {
        when:
        Validator.isValidChaveAleatoria(paramValue, TipoChaveEnum.ALEATORIO)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue                               | result
        "AAAABBBBCCCCDDDDEEEEFFFFIIIIJJJJKKKKLK" | null
    }

    def "validate conta when valid"() {
        when:
        def isValid = Validator.isValidConta(paramValue)

        then:
        isValid == result

        where:

        paramValue | result
        12345678   | true
        818106     | true
        3456       | true
    }

    def "validate conta when invalid"() {
        when:
        Validator.isValidConta(paramValue)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue | result
        123456789  | null
    }

    def "validate agencia when valid"() {
        when:
        def isValid = Validator.isValidAgencia(paramValue)

        then:
        isValid == result

        where:

        paramValue | result
        1234       | true
        432        | true
        12         | true
    }

    def "validate agencia when invalid"() {
        when:
        Validator.isValidAgencia(paramValue)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue | result
        12345      | null
    }

    def "validate nome when valid"() {
        when:
        def isValid = Validator.isValidNome(paramValue)

        then:
        isValid == result

        where:

        paramValue | result
        "Jose"     | true
        "Joseph"   | true
        "Joao"     | true
    }

    def "validate nome when invalid"() {
        when:
        Validator.isValidNome(paramValue)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue                                             | result
        "pedropauloaparecidodasilvanetojuniorsilveiraferreira" | null
    }


    def "validate sobrenome when valid"() {
        when:
        def isValid = Validator.isValidSobrenome(paramValue)

        then:
        isValid == result

        where:

        paramValue | result
        "Costa"    | true
        "Silveira" | true
        "Mendes"   | true
    }

    def "validate sobrenome when invalid"() {
        when:
        Validator.isValidSobrenome(paramValue)

        then:
        thrown(UnprocessableEntity)

        where:

        paramValue                                                   | result
        "pedropauloaparecidodasilvanetojuniorsilveiraferreiramendes" | null
    }
}
