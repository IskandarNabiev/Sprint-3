package test.kotlin.ru.sber.qa


import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.Assert.assertThrows
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import ru.sber.qa.ScanTimeoutException
import ru.sber.qa.Scanner
import kotlin.random.Random

internal class ScannerTest {

    @Test
    fun throwScanDataException() {
        mockkObject(Random)
        every { Random.nextLong(1000L, 15000L) } returns 12000L
        assertThrows(ScanTimeoutException::class.java) { Scanner.getScanData() }
    }

    @Test
    fun getScanData() {
        mockkObject(Random)
        every { Random.nextLong(1000L, 15000L) } returns 10000L
        assertDoesNotThrow { Scanner.getScanData() }
    }

    @AfterEach
    fun afterTest() {
        unmockkAll()
    }
}