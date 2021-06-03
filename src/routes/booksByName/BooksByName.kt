package com.demo.app.routes.booksByName

import com.demo.app.model.Book
import com.google.gson.GsonBuilder
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.nio.channels.UnresolvedAddressException
import java.util.*

data class BookResponseBody (
  val results: List<Book>
)

suspend fun grabBooksFromRemoteServer (): Book {
  val client:HttpClient = HttpClient(CIO)
  return try {
    val response:HttpResponse = client.get("https://uncovered-treasure-v1.p.rapidapi.com/random") {
      headers {
        append("x-rapidapi-key", dotenv()["API_KEY"])
      }
    }
    client.close()
    GsonBuilder().create().fromJson(response.readText(), BookResponseBody::class.java).results[0]
  } catch (e: UnresolvedAddressException) {
    Book("Not found", "NONE", listOf(), scriptures = listOf(), date = Date().toString())
  }
}
