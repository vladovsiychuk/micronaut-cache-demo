package com.example.service

import io.reactivex.Flowable
import io.reactivex.Single
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class TestService {

    private val logger: Logger = LoggerFactory.getLogger(TestService::class.java)

    fun test(): Flowable<Pair<String, String>> {
        return flowableOfListLetters()
            .flatMap { letters ->
                processLetters(letters)
                    .toFlowable()
                    .flatMap { processedLetters ->
                        Flowable.fromIterable(processedLetters.entries)
                    }
                    .map { entry ->
                        Pair(entry.key.uppercase(), entry.value)
                    }
            }
    }

    private fun processLetters(letters: List<String>) : Single<Map<String, String>> {
        logger.info("Inside processLetters")

        val letterMap = letters.associateWith { it.uppercase() }

        return Single.just(letterMap)
    }

    private fun flowableOfListLetters(): Flowable<List<String>> {
        return Flowable.fromIterable(listOf(listOf("a", "b"), listOf("c", "d")))
    }
}
