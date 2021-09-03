package pix.br.com.zup.validacao


import java.util.regex.Pattern.CASE_INSENSITIVE
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern

import javax.validation.constraints.Pattern.Flag.*
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@ReportAsSingleViolation
@Constraint(validatedBy = [])
@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$",
    flags = [Pattern.Flag.CASE_INSENSITIVE])
@Retention(AnnotationRetention.RUNTIME)
@Target(FIELD, CONSTRUCTOR, PROPERTY, VALUE_PARAMETER)
annotation class UUIDValido(
    val message: String = "não é um UUID válido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
