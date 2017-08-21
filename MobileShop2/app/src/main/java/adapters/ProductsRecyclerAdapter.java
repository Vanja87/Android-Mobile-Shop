package adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.mobileshop2.R;

import java.util.List;

import model.Products;

/**
 * Created by PC on 7/18/2017.
 */

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ProductsViewHolder> {

    private List<Products> listProducts;

    public ProductsRecyclerAdapter(List<Products> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products_recycler, parent, false);

        return new ProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.textViewCategory.setText(listProducts.get(position).getCategory());
        holder.textViewUsed.setText(listProducts.get(position).getUsed());
        holder.textViewName.setText(listProducts.get(position).getName());
        holder.textViewDescription.setText(listProducts.get(position).getDescription());
        holder.textViewPrice.setText(listProducts.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        Log.v(ProductsRecyclerAdapter.class.getSimpleName(),""+listProducts.size());
        return listProducts.size();
    }


    /**
     * ViewHolder class
     */
    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewCategory;
        public AppCompatTextView textViewUsed;
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewDescription;
        public AppCompatTextView textViewPrice;

        public ProductsViewHolder(View view) {
            super(view);
            textViewCategory = (AppCompatTextView) view.findViewById(R.id.textViewCategory);
            textViewUsed = (AppCompatTextView) view.findViewById(R.id.textViewUsed);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewDescription = (AppCompatTextView) view.findViewById(R.id.textViewDescription);
            textViewPrice = (AppCompatTextView) view.findViewById(R.id.textViewPrice);
        }
    }


}