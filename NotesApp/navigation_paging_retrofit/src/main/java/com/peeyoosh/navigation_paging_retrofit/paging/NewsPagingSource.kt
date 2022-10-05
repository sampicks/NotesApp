package com.peeyoosh.navigation_paging_retrofit.paging

import androidx.paging.PagingSource
import com.peeyoosh.navigation_paging_retrofit.retrofit.NewsInterface
import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel
import com.peeyoosh.navigation_paging_retrofit.util.Constant
import retrofit2.HttpException
import java.io.IOException

const val STARTING_INDEX = 1

class NewsPagingSource(val newsInterface: NewsInterface) : PagingSource<Int, ArticleDataModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDataModel> {
        val position = params.key ?: STARTING_INDEX

        return try {
            val data = newsInterface.getAllNews(
                "us",
                "business",
                Constant.API_KEY,
                position,
                params.loadSize
            )
            return LoadResult.Page(
                data = data.articles!!,
                prevKey = if (params.key == STARTING_INDEX) null else position - 1,
                nextKey = if (data.articles!!.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}