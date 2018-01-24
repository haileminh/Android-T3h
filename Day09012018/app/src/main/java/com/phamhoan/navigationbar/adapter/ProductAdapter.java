package com.phamhoan.navigationbar.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.phamhoan.navigationbar.IDataBack;
import com.phamhoan.navigationbar.OnLoadMoreListener;
import com.phamhoan.navigationbar.R;
import com.phamhoan.navigationbar.model.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;



public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ProductAdapter1";
    private Context context;
    private LayoutInflater inflater;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<Product> productList, filterList;
    private IDataBack iDataBack;

    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading = false;
    private int lastVisibleItem, totalItemCount;
    private boolean checkSearch = false;


    public void addList(List<Product> productList) {
        this.productList.addAll(productList);
        this.filterList.addAll(productList);
    }

    public ProductAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        productList = new ArrayList<>();
        filterList = new ArrayList<>();
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= lastVisibleItem + 1 && !checkSearch) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });

    }

    public void setDataBack(IDataBack iDataBack) {
        this.iDataBack = iDataBack;
    }

    @Override
    public int getItemViewType(int position) {
        return productList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_product, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.item_loading, parent, false);
            LoadingViewHolder loadingViewHolder = new LoadingViewHolder(view);
            return loadingViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            Product product = productList.get(position);
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.txtName.setText(product.getName());
            holder1.txtCategory.setText(product.getCategory());
            holder1.txtCreatedDate.setText(coverDate(product.getCreatedDate()));
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iDataBack.dataBack(position, 1);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    PopupMenu popupMenu = new PopupMenu(context, holder.itemView);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit:
                                    iDataBack.dataBack(position, 2);
                                    return true;
                                case R.id.delete:
                                    callDelete();

                                    return true;
                                default:
                                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                                    return true;
                            }

                        }

                        private void callDelete() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Bạn có chắc muốn xóa ?");
                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    iDataBack.dataDelete(position);
                                    productList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeRemoved(position, getItemCount());
                                }
                            });
                            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }


    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtCategory;
        private TextView txtCreatedDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_Name);
            txtCategory = itemView.findViewById(R.id.txt_Category);
            txtCreatedDate = itemView.findViewById(R.id.txt_CreateDate);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    private String coverDate(String unix) {
        long unix1 = Long.parseLong(unix);
        Date date = new Date(unix1 * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy ");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }


    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        this.filterList = productList;

    }


    public List<Product> getProductList() {
        return productList;
    }

    public void filter(final String text) {

        productList.clear();


        if (text.isEmpty()) {
            checkSearch = false;
            productList.addAll(filterList);

        } else {
            checkSearch = true;
            for (Product item :filterList) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    productList.add(item);
                }
            }
        }

        notifyDataSetChanged();


    }
}
