import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString

/**
 * Reads lines from the given input txt file.
 */
fun readStrings(name: String) = readInput(name).lines()

fun readInput(name: String) = SystemFileSystem.source(Path("tasks/$name.txt")).buffered().readString().trim()
