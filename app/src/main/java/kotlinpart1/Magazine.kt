package kotlinpart1

class Magazine(override val price: Int, override val wordCount: Int) : Publication {

    override fun getType(s: String): String {
        return "Magazine"
    }

}