package com.booksfloating.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.booksfloating.domain.BooksRecommendBean;
import com.booksfloating.util.LoaderImageUseVelloy;
import com.xd.booksfloating.R;

public class BookRecommendAdapter extends BaseAdapter{
	List<BooksRecommendBean> booksBeanList;

	LayoutInflater mLayoutInflater;
	
	Context myContext;

	
	public BookRecommendAdapter(Context context, List<BooksRecommendBean> booksBeanList) {
		super();
		this.booksBeanList = booksBeanList;
		
		this.mLayoutInflater = LayoutInflater.from(context);
		this.myContext = context;
		
		
	}
	
	
	
	@Override
	public int getCount() {
		return booksBeanList.size();
	}

	@Override
	public Object getItem(int position) {
		return booksBeanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.booklist_item, null);
			viewHolder.bookName = (TextView) convertView.findViewById(R.id.sh_book_name);
			viewHolder.bookAuthor = (TextView) convertView.findViewById(R.id.sh_book_author);
			viewHolder.bookImage = (ImageView) convertView.findViewById(R.id.sh_book_image);
			viewHolder.bookRanking = (TextView) convertView.findViewById(R.id.sh_book_ranking);
			convertView.setTag(viewHolder);
			
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		viewHolder.bookName.setText(booksBeanList.get(position).bookName);
		viewHolder.bookAuthor.setText(booksBeanList.get(position).bookAuthor);
		viewHolder.bookImage.setImageResource(android.R.id.icon);
		String url = booksBeanList.get(position).bookImageUrl;
		viewHolder.bookImage.setTag(url);
		//setImageView(url, viewHolder.bookImage);//加载速度太慢！
		//new LoadBookImage().showImageByThread(viewHolder.bookImage, url);没有网打不开
		
		new LoaderImageUseVelloy().LoaderImage(myContext, viewHolder.bookImage, url);
		//内存溢出，使用单例模式解决，context不能为Activity，必须是context.getApplicationContext()才可以
		
		if(position == 0){
			viewHolder.bookRanking.setText("No.1");
			viewHolder.bookRanking.setBackgroundResource(R.drawable.bg_books_ranking1);
		}else if(position == 1){
			viewHolder.bookRanking.setText("No.2");
			viewHolder.bookRanking.setBackgroundResource(R.drawable.bg_books_ranking2);
		}else if(position == 2){
			viewHolder.bookRanking.setText("No.3");
			viewHolder.bookRanking.setBackgroundResource(R.drawable.bg_books_ranking3);
		}
		
		return convertView;
	}
	class ViewHolder{
		ImageView bookImage;
		TextView bookName;
		TextView bookAuthor;
		TextView bookRanking;
	}

}
