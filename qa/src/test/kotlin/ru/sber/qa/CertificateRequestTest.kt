package test.kotlin.ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import ru.sber.qa.CertificateRequest
import ru.sber.qa.CertificateType
import ru.sber.qa.Scanner
import kotlin.random.Random

internal class CertificateRequestTest {

    private var employeerNumber = 60L
    private var hrEmployeerNumber = 20L
    private var certificateType = mockk<CertificateType>()
    private var certificateRequest = CertificateRequest(employeerNumber, certificateType)
    private var randomData = Random.Default.nextBytes(200)


    @Test
    fun process() {
        mockkObject(Scanner)
        every { Scanner.getScanData() } returns randomData

        val certificate = certificateRequest.process(hrEmployeerNumber)

        assertNotNull(certificate)
        assertEquals(certificateRequest, certificate.certificateRequest)
        assertEquals(randomData, certificate.data)
    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeerNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }

    @AfterEach
    fun afterTest() {
        unmockkAll()
    }
}