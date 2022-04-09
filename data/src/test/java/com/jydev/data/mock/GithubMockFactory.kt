package com.jydev.data.mock

import com.jydev.data.model.remote.SearchUserResponse

class GithubMockFactory {
    fun makeSearchUserResponse(): SearchUserResponse =
        SearchUserResponse(false,makeItemResponseList(),0)

    private fun makeItemResponseList(): List<SearchUserResponse.ItemResponse> {
        val list = mutableListOf<SearchUserResponse.ItemResponse>()
        for (i in 0 until 5) {
            val id = i.toLong()
            list.add(SearchUserResponse.ItemResponse(
                "${id}.jpg",
                "",
                "",
                "",
                "",
                "",
                "",
                id,
                "",
                "",
                "",
                "",
                "",
                0, false, "", "","",""
            ))
        }
        return list
    }

}