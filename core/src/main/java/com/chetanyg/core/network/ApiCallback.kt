package com.chetanyg.core.network

/**
 * The base class for all api responses.
 */
interface ApiCallback<response> {

    fun onFailure(error: Throwable)

    fun onSuccess(response: response)

}
