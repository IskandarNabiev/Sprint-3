package test.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import ru.sber.qa.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

internal class HrDepartmentTest {

    private var certificateRequest = mockk<CertificateRequest>()
    private var certificate = mockk<Certificate>()
    private var hrEmployeeNumber = 20L

    @Test
    fun throwNotAllowRequest() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-14T12:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        assertThrows(NotAllowReceiveRequestException::class.java) {HrDepartment.receiveRequest(certificateRequest)}
    }

    @Test
    fun throwWeekendException() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-12T12:00:00Z"), ZoneOffset.UTC)
        assertThrows(WeekendDayException::class.java) { HrDepartment.receiveRequest(certificateRequest) }
    }

    @Test
    fun processNextRequest() {
        HrDepartment.clock = Clock.fixed(Instant.parse("2021-09-08T12:00:00Z"), ZoneOffset.UTC)
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        every { certificateRequest.process(hrEmployeeNumber) } returns certificate
        HrDepartment.receiveRequest(certificateRequest)
        assertDoesNotThrow { HrDepartment.processNextRequest(hrEmployeeNumber) }
    }

    @AfterEach
    fun afterTest() {
        unmockkAll()
    }
}