# Easiest Generic Recycler Adapter

Easiest way to use RecyclerView. Reduce boilerplate code! You don't need to write adapters for listing pages anymore!

You can check it out [Demo Application here](https://github.com/burakeregar/EasiestGenericRecyclerDemo)

[![](https://jitpack.io/v/burakeregar/EasiestGenericRecyclerAdapter.svg)](https://jitpack.io/#burakeregar/EasiestGenericRecyclerAdapter)
[![](https://img.shields.io/badge/Android%20Arsenal-EasiestGenericRecyclerAdapter-green.svg?style=flat)](https://android-arsenal.com/details/1/5701)
### How to Setup:
* Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
* Step 2. Add the dependency
```java
	dependencies {
		compile 'com.github.burakeregar:EasiestGenericRecyclerAdapter:v1.2'
	}
```
### How to Use:

Create your ViewHolder class & extend GenericViewHolder 

```java
public class YourViewHolder extends GenericViewHolder {
    private YourModel mItem;

    public YourViewHolder(final View itemView) {
        super(itemView);
    }

    public void bindData(final Object element) {
        mItem = (YourModel) element;
        // set whatever you want. for instance;
        surname.setText(mItem.getSurname());
        name.setText(mItem.getName());
    }
}
```
In your activity/fragment class you can initialize adapter like below;
```java
GenericRecyclerAdapter mAdapter;
RecyclerView mMainRv;
```
```java
    mMainRv.setLayoutManager(new LinearLayoutManager(this)); //set your layout manager
    mAdapter = new GenericAdapterBuilder()
                .addModel(
    		        R.layout.contact_row, //set your row's layout file
                        YourViewHolder.class, //set your view holder class
                        YourModel.class) // set your model class(If you use just String list, it can be just String.class)
                .execute();
    mMainRv.setAdapter(mAdapter);
    
```
If your list contains different row types, you can add them like below;
```java
   mAdapter = new GenericAdapterBuilder()
                .addModel(
    		        R.layout.contact_row, 
                        YourViewHolder.class, 
                        YourModel.class)
                .addModel(
    		        R.layout.second_row, 
                        YourSecondViewHolder.class, 
                        YourSecondModel.class)
                .addModel(
    		        R.layout.third_row, 
                        YourThirdViewHolder.class, 
                        YourThirdModel.class)
                .execute(); 
    
```
Now you are ready to set your list!

Assume that you have 
```java
List<YourModel> mList
```
or
```java
List<Object> mList
```
then you can set your list like this.
```java
mAdapter.setList(mList);
```
if you want to add new row(s) to your existing list, you can use addNewRows method. You can pass any object or object list to addNewRows method like below;
```java
mAdapter.addNewRows(mList);
```
or
```java
YourModel lItem;
mAdapter.addNewRows(lItem);
```

You can use getItems method to get current items of the list;

```java
List<YourModel> mList = mAdapter.getItems();
```

### Using with Filter
If you want to use filter with your recyclerview for searching etc. you have to enable & set filter like below;
```java
mAdapter = new GenericAdapterBuilder()
                .addModel(
    		       R.layout.contact_row, 
                       YourViewHolder.class, 
                       YourModel.class)
                .setFilterEnabled() //important!
                .execute();
                
 mAdapter.setFilter(new SearchFilter(mAdapter));
```

Example search filter class;

```java
public class SearchFilter extends Filter {

   private GenericRecyclerAdapter mAdapter;

   public SearchFilter(GenericRecyclerAdapter pAdapter) {
      mAdapter = pAdapter;
   }

   @Override
   protected FilterResults performFiltering(CharSequence constraint) {
     
      FilterResults results = new FilterResults();

      final List<Object> list = mAdapter.mOriginalObjectList;

      final ArrayList<Object> filteredList = new ArrayList<>();

      String filterableString = "";

      for (Object lModel : list) {
         if (lModel instanceof YourModel) {
            filterableString = ((YourModel) lModel).getValue();
         }  
         if (filterableString.contains(constraint.toString())) {
            filteredList.add(lModel);
         }
      }

      results.values = filteredList;
      results.count = filteredList.size();

      return results;
   }

   @Override
   protected void publishResults(CharSequence constraint, FilterResults results) {
      mAdapter.mObjectList = (List<Object>) results.values;
      mAdapter.notifyDataSetChanged();
   }
}
```
you can set & listen the result in your edittext's afterTextChanged method;
```java
mAdapter.getFilter().filter(editable.toString(), new Filter.FilterListener() {
                  @Override
                  public void onFilterComplete(int pI) {
                     if (mAdapter.mObjectList == null || mAdapter.mObjectList.size() <= 0) {
		     //If the search key is deleted, set the original list
                       mAdapter.addItem(mAdapter.mOriginalObjectList);
                     }
                  }
               });
   ```
   
### Contribution
Feel free to add new features to the library, I am happy to accept pull requests.

### PROGUARD CONFIGURATION

Add this line to your proguard file
```java
-keep class com.burakeregar.easiestgenericrecycleradapter.base.** { *; }
```
   
### LICENSE

MIT License

Copyright (c) 2017 Burak Eregar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
