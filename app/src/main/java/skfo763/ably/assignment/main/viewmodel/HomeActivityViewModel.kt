package skfo763.ably.assignment.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import skfo763.ably.assignment.main.data.ProductItem
import skfo763.ably.assignment.main.data.ProductRecyclerData
import skfo763.ably.assignment.main.data.toViewItem
import skfo763.ably.base.BaseViewModel
import skfo763.ably.base.extension.logException
import skfo763.ably.base.extension.plusAssign
import skfo763.ably.base.extension.safeValue
import skfo763.ably.domain.HomeRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel() {

    private val _swipeLoading = MutableLiveData(false)
    private val _productItemList = MutableLiveData<List<ProductRecyclerData>>()
    private val _productLikeResult = MutableLiveData<ProductItem>()
    private val _additionalProductItemList = MutableLiveData<List<ProductRecyclerData>>()
    private val _likedItemList = MutableLiveData<List<ProductItem>>()

    val swipeLoading: LiveData<Boolean> = _swipeLoading
    val productItemList: LiveData<List<ProductRecyclerData>> = _productItemList
    val productLikeResult: LiveData<ProductItem> = _productLikeResult
    val additionalProductItemList: LiveData<List<ProductRecyclerData>> = _additionalProductItemList
    val likedItemList: LiveData<List<ProductItem>> = _likedItemList


    private val onLikeButtonClicked: (ProductItem) -> Unit = {
        if(it.liked) {
            dislike(it)
        } else {
            like(it)
        }
    }

    private val checkItemLiked: (ProductRecyclerData) -> ProductRecyclerData = {
        when(it) {
            is ProductItem -> it.apply {
                it.liked = _likedItemList.value?.find { item -> item.productId == it.productId } != null
            }
            else -> it
        }
    }

    fun getHomeData() {
        compositeDisposable += repository.getHomeData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val productList = mutableListOf<ProductRecyclerData>().apply {
                    it.bannerList?.toViewItem()?.let { add(it) }
                    addAll(it.productList.map { prod ->
                        prod.toViewItem(onLikeClicked = onLikeButtonClicked)
                    })
                }.map(checkItemLiked)
                _productItemList.safeValue = productList
                _swipeLoading.safeValue = false
            }) {
                logException(it)
            }
    }

    fun getMoreGoods(lastIdx: Int) {
        compositeDisposable += repository.getMoreGoods(lastIdx)
            .subscribe({
                _additionalProductItemList.safeValue = it.productList.map { prod ->
                    prod.toViewItem(onLikeClicked = onLikeButtonClicked)
                }.map(checkItemLiked)
            }) {
                logException(it)
            }
    }

    fun getLikeData() {
        compositeDisposable += repository.getLikedProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _likedItemList.safeValue = it.map { prod ->
                    prod.toViewItem(itemType = ProductItem.ItemType.LIKE)
                }
                _swipeLoading.safeValue = false
            }) {
                logException(it)
            }
    }

    private fun like(data: ProductItem) {
        repository.addLikedProductToDb(data.toDomainData())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _productLikeResult.safeValue = data.apply { liked = true }
            }
    }

    private fun dislike(data: ProductItem) {
        repository.deleteLikedProductToDb(data.toDomainData())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _productLikeResult.safeValue = data.apply { liked = false }
            }
    }

    fun setSwipeLoading(isSwipe: Boolean) {
        _swipeLoading.safeValue = isSwipe
    }

}