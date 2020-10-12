import kotlinx.coroutines.*
import java.io.File
import javax.sound.sampled.AudioSystem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

suspend fun playBeats(beats: String, file: String){
    val parts = beats.split("x")
    var count = 0
    for (part in parts){
        count += part.length + 1
        if (part == "")playSound(file)
        else{
            delay(100 * (part.length + 1L))
            if(count < beats.length)playSound(file)
        }
    }
}

fun playSound(file: String) {
    val clip = AudioSystem.getClip()
    val audioInputStream = AudioSystem.getAudioInputStream(
        File(
            file
        )
    )
    clip.open(audioInputStream)
    clip.start()
}

fun main(){
    runBlocking {
            launch {
            playBeats("x-x-x-x-x-x-", "audio/toms.aiff")
        }
        playBeats("x-----x-----","audio/crash_cymbal.aiff")
    }

}

class Totaller(var total:Int = 0){
    fun add(num:Int):Int{
        total += num
       return total
    }
}

class TotallerTest {
    val totaller = Totaller()
    @Test
    fun shouldBeAbleToAdd3Adn4(){
        assertEquals(3, totaller.add(3))
        assertEquals(7, totaller.add(4))
        assertEquals(7, totaller.add(1))
    }
}