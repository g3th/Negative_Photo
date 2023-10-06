import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage
import kotlin.random.Random
import kotlin.system.exitProcess

/* 0 TYPE_CUSTOM 1 TYPE_INT_RGB 2 TYPE_INT_ARGB 3 TYPE_INT_ARGB_PRE 4 TYPE_INT_BGR 5 TYPE_3BYTE_BGR 6 TYPE_4BYTE_ABGR
   7 TYPE_4BYTE_ABGR_PRE 8 TYPE_USHORT_565_RGB 9 TYPE_USHORT_555_RGB 10 TYPE_BYTE_GRAY 11 TYPE_USHORT_GRAY
   12 TYPE_BYTE_BINARY 13 TYPE_BYTE_INDEXED*/

class ImageMethods {
    private val directory = System.getProperty("user.home")
    private val file = File("${directory}/")
    private var counter = 10
    private lateinit var randomColours: Color
    private var imageRGB: Int = 0
    private lateinit var imageColours: Color
    private lateinit var image: BufferedImage
    private var randomColour = listOf<Int>()
    private lateinit var newImage: BufferedImage

    fun readImage(f: File) {
        image = ImageIO.read(f)
    }

    private fun randomColour(){
        randomColour = listOf(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
        println(Color(0,0,0).rgb)
        println(image.getRGB(0,0))
    }

    fun manipulateImage(f: File) {
        readImage(f)
        newImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_3BYTE_BGR)
        for (i in 0..newImage.width - 1){
            for (j in 0..newImage.height - 1){
                imageRGB = image.getRGB(i, j)
                imageColours = Color(255,255,255)
                newImage.setRGB(i, j, imageColours.rgb - imageRGB)
            }
        }
    }

    fun createEmptyImage(f: File, w: Int, h: Int) {
        image = BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR)
        ImageIO.write(image, "png", f)
    }

    fun saveImage(f: File) {
        ImageIO.write(newImage, "png", f)
    }
}

fun main(args: Array<String>){
    val imageMethods = ImageMethods()
    var rwFile: File
    for (i in args.indices) {
        when (args[i]) {
            "-create" -> {
                println("Actually.....No......")
                exitProcess(0)
            }
            "-in" -> {
                rwFile = File("${args[i + 1]}")
                imageMethods.manipulateImage(rwFile)
            }
            "-out" -> {
                rwFile = File("${args[i + 1]}")
                imageMethods.saveImage(rwFile)
            }
        }
    }
}
