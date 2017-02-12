package com.dejar.de.fumar;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList;
	
	private Integer[] imgid = {
			R.drawable.i1,
			R.drawable.i2,
			R.drawable.i3,
			R.drawable.i4,
			R.drawable.i5,
			R.drawable.i6,
			R.drawable.i7,
			R.drawable.i8,
			R.drawable.i9,
			R.drawable.i10,
			R.drawable.i11,
			R.drawable.i12,
			R.drawable.i13,
			R.drawable.i14,
			R.drawable.i15,
			R.drawable.i16,
			R.drawable.i17,
			R.drawable.i18,
			R.drawable.i19,
			R.drawable.i20,
			R.drawable.i21,
			R.drawable.i22,
			R.drawable.i23,
			R.drawable.i24,
			R.drawable.i25,
			R.drawable.i26,
			R.drawable.i27			
			};
	
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.logros_row, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
			holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);
			holder.itemImagecopa = (ImageView) convertView.findViewById(R.id.copas);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());
		holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getPrice());
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
		holder.itemImagecopa.setImageResource(R.drawable.copa4);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		//if (holder.txt_itemPrice.getText().equals("1"))
		if (itemDetailsrrayList.get(position).getPrice().equals("1"))
		{
			convertView.setBackgroundColor(Color.parseColor("#D4EEEB"));
		    holder.itemImagecopa.setVisibility(View.VISIBLE);
		}
		else
		{
			convertView.setBackgroundColor(Color.WHITE);
			holder.itemImagecopa.setVisibility(View.INVISIBLE);
		}
		
		return convertView;
	}

	static class ViewHolder 
	{
		ImageView itemImagecopa;
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemPrice;
		ImageView itemImage;
	}
}
