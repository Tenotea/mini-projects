package com.demo.app

import com.demo.app.routes.booksByName.grabBooksFromRemoteServer
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
@kotlin.jvm.JvmOverloads

fun Application.main(testing: Boolean = false) {
  install(ContentNegotiation) {
    gson() {
      setPrettyPrinting()
    }
  }

  routing {
    get("/") {
      call.respond(grabBooksFromRemoteServer())
    }
  }
}
