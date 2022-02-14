import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.names.DuplicateTestNameMode

class TestProjectConfiguration : AbstractProjectConfig() {
    override val duplicateTestNameMode = DuplicateTestNameMode.Silent
}