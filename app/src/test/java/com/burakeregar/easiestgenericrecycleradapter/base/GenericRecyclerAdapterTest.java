package com.burakeregar.easiestgenericrecycleradapter.base;

import com.burakeregar.easiestgenericrecycleradapter.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class GenericRecyclerAdapterTest {

    private static final int SOME_LAYOUT_ID_1 = 12321321;
    private static final Class SOME_HOLDER_TYPE = GenericViewHolder.class;
    private static final Class SOME_ITEM_TYPE = String.class;
    private static final String SOME_ITEM = "Lorem Ipsum";

    private GenericRecyclerAdapter subject;
    private int itemCountResult = -1;
    private int itemViewTypeResult = -1;
    private GenericViewHolder mockViewHolder = mock(GenericViewHolder.class);


    @Before
    public void setUp() {
        subject = new GenericRecyclerAdapter(SOME_LAYOUT_ID_1, SOME_HOLDER_TYPE, SOME_ITEM_TYPE);
    }

    @Test
    public void whenGetItemCount_givenOneItem_thenReturnsItemCount() {
        givenOneItemIsAdded();
        whenGetItemCount();
        thenItemCountIs(1);
    }

    @Test
    public void whenGetItemCount_givenTwoItems_thenReturnsItemCount() {
        givenOneItemIsAdded();
        givenOneItemIsAdded();
        whenGetItemCount();
        thenItemCountIs(2);
    }

    @Test
    public void whenOnBindViewHolder_givenItemIsSet_thenBindData() {
        givenOneItemIsAdded();
        whenOnBindViewHolder();
        thenHolderBindDataIsCalled();
    }

    @Test
    public void whenGetItemViewType_givenPosition_thenReturnsViewTypePosition() {
        givenOneItemIsAdded();
        whenGetItemViewType();
        thenReturnsViewTypePosition();
    }

    private void thenReturnsViewTypePosition() {
        assertEquals(itemViewTypeResult, 0);
    }

    private void whenGetItemViewType() {
        itemViewTypeResult = subject.getItemViewType(0);
    }

    private void thenHolderBindDataIsCalled() {
        then(mockViewHolder).should().bindData(subject.mObjectList.get(0));
    }

    private void whenOnBindViewHolder() {
        subject.onBindViewHolder(mockViewHolder, 0);
    }

    private void thenItemCountIs(int expected) {
        assertEquals(itemCountResult, expected);
    }

    private void whenGetItemCount() {
        itemCountResult = subject.getItemCount();
    }

    private void givenOneItemIsAdded() {
        subject.addNewRows(SOME_ITEM);
    }

}