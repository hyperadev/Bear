/*
 * Bear - Simple debugging library
 *  Copyright (c) 2022 Joshua Sing
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
import com.adarshr.gradle.testlogger.theme.ThemeType
import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("bear.publishing")
    id("net.kyori.indra.git")
    id("net.kyori.indra.checkstyle")
    id("net.kyori.indra.license-header")
    id("net.kyori.blossom")

    id("com.adarshr.test-logger")
    id("net.ltgt.errorprone")
}


testlogger {
    theme = ThemeType.MOCHA_PARALLEL
}

blossom {
    replaceToken("@version@", rootProject.version)
    replaceToken("@commit@", indraGit.commit()?.name?.substring(0, 7) ?: "unknown")
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.23.0")
    annotationProcessor("com.uber.nullaway:nullaway:0.10.1")
}

tasks {
    compileJava {
        options.errorprone {
            disable("UnusedVariable")
            error("NullAway")

            option("NullAway:AnnotatedPackages", "dev.hypera.chameleon")
        }
    }
}
