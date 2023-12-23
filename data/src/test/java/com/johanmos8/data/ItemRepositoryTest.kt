package com.johanmos8.data

import com.johanmos8.data.networking.MeliApi
import com.johanmos8.data.remotedatasource.RemoteDataSource
import com.johanmos8.data.repository.ItemRepositoryImpl
import com.johanmos8.domain.model.ItemDetail
import com.johanmos8.domain.repository.ItemRepository
import com.johanmos8.domain.util.Resource
import com.johanmos8.domain.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemRepositoryTest {
    @Mock
    lateinit var remoteDataSource: RemoteDataSource


    private lateinit var itemRepository: ItemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        itemRepository = ItemRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `given a string when get list of items by search then return a list`() = runBlocking {
        //Given
        val query = "test"

        val listItemsMock: List<ItemDetail> = List(2) { Mockito.mock(ItemDetail::class.java) }
        val flowItemsMock: Flow<Resource<List<ItemDetail>>> =
            flowOf(Resource.success(listItemsMock))

        `when`(remoteDataSource.getItemBySearch(query)).thenReturn(flowItemsMock)
        //When

        val result = itemRepository.getItemBySearch("test").first()
        //Then
        val expected: Resource<List<ItemDetail>> = Resource.success(listItemsMock)
        assertEquals(expected, result)
    }

    @Test
    fun `given a string when get list of items by search then return an error`() = runBlocking {
        // Given
        val query = "test"
        val expectedErrorMessage = "Simulated error message"

        // When

        val flowError: Flow<Resource<List<ItemDetail>>> =
            flowOf(Resource.error(expectedErrorMessage, null))
        `when`(remoteDataSource.getItemBySearch(query)).thenReturn(
            flowError
        )
        // When
        val result = itemRepository.getItemBySearch(query).first()

        // Then
        assertTrue(result.status == Status.ERROR)
        assertNull(result.data)

    }

    @Test
    fun `given an item id when get item detail then return an item`() = runBlocking {
        // Given
        val itemId = "testId"
        val itemMock: ItemDetail = Mockito.mock(ItemDetail::class.java) as ItemDetail
        val ItemsResponse: Resource<ItemDetail> =
            Resource.success(itemMock)

        // When

        `when`(remoteDataSource.getItemDetails(itemId)).thenReturn(ItemsResponse)
        val result = itemRepository.getItemDetail(itemId)
        // Then
        assertTrue(result.status == Status.SUCCESS)
        assertTrue(result.data != null)
    }

    @Test
    fun `given an item id when get item detail then return an error`() = runBlocking {
        // Given
        val itemId = "testId"
        val expectedErrorMessage = "Simulated error message"
        `when`(remoteDataSource.getItemDetails(itemId)).thenReturn(
            Resource.error(expectedErrorMessage, null)
        )

        // When
        val response = itemRepository.getItemDetail(itemId)

        // Then
        assert(response.status == Status.ERROR)
        assert(response.message != null)
    }
}