package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        val filePath = "io/logfile.log"
        val zipFilePath = "io/logfile.zip"
        val buffer: ByteArray
        val zipEntry: ZipEntry


        val output  = ZipOutputStream(FileOutputStream(zipFilePath))
        val input = FileInputStream(filePath)

        input.use {
            buffer = ByteArray(input.available())
            input.read(buffer)
        }

        output.use {
            zipEntry = ZipEntry(filePath)
            output.write(buffer)
            output.closeEntry()
        }

    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {

        val filePath = "io/unzippedLogfile.log"
        val zipFilePath = "io/logfile.zip"
        val buffer: ByteArray


        val inputStream = ZipInputStream(FileInputStream(zipFilePath))
        val outputStream = FileOutputStream(filePath)

        inputStream.use{
            inputStream.nextEntry
            buffer = inputStream.readBytes()
            inputStream.closeEntry()
        }

        outputStream.use {
            outputStream.write(buffer)
        }

        }


}