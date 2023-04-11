package com.example.service

import io.reactivex.Flowable
import io.reactivex.Single
import jakarta.inject.Singleton

@Singleton
class TestService {

    fun test() : Flowable<Map<String, String>> {
        return fluxOfListLetters()
            .flatMap {
                processLetters(it)
                    .toFlowable()
            }
    }

    private fun processLetters(letters: List<String>) : Single<Map<String, String>> {
        val letterMap = letters.associateWith { it.uppercase() }

        return Single.just(letterMap)
    }

    private fun fluxOfListLetters(): Flowable<List<String>> {
        return Flowable.fromIterable(listOf(listOf("a","b"), listOf("a","d")))
    }
}
