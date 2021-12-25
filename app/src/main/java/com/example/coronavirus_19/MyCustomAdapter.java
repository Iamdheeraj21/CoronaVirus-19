    package com.example.coronavirus_19;

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;

    import java.util.ArrayList;


    public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.CustomViewholder>
{
    Context context;
    ArrayList<CountryModel> adapters;

    public MyCustomAdapter(Context context, ArrayList<CountryModel> adapters) {
        this.context = context;
        this.adapters = adapters;
    }
    @NonNull
    @Override
    public CustomViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new CustomViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewholder holder, @SuppressLint("RecyclerView") int position)
    {
        CountryModel countryModel=adapters.get(position);
        String flagImageUrl= countryModel.getFlag();
        String countryName= countryModel.getCountry();

        holder.textView.setText(countryName);
        Glide.with(context).load(flagImageUrl).placeholder(R.drawable.ic_baseline_flag_24).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(context,DetailsActivity.class);
                intent.putExtra("cName",countryName);
                intent.putExtra("cTotalDeaths",countryModel.getDeaths());
                intent.putExtra("cTodayDeaths",countryModel.getTodayDeaths());
                intent.putExtra("cCases",countryModel.getTodayCases());
                intent.putExtra("cTotalCases",countryModel.getCases());
                intent.putExtra("cactive",countryModel.getActive());
                intent.putExtra("cCritical",countryModel.getCritical());
                intent.putExtra("cRecovered",countryModel.getRecovered());
                intent.putExtra("cUrl",flagImageUrl);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return adapters.size();
    }

    static class CustomViewholder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public CustomViewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_CountryName);
            imageView=itemView.findViewById(R.id.flagImage);
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void FilteredList(ArrayList<CountryModel> list)
    {
        adapters=list;
        notifyDataSetChanged();
    }
}


/*public class MyCustomAdapter extends ArrayAdapter<CountryModel>
{

    Context context;
    List<CountryModel> list;

    public MyCustomAdapter(@NonNull Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_item);
        this.context=context;
        this.list=countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        @SuppressLint({"ViewHolder", "InflateParams"})
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null,true);
        TextView textView;
        ImageView imageView;
        textView=view.findViewById(R.id.text_CountryName);
        imageView=view.findViewById(R.id.flagImage);

        textView.setText(list.get(position).getCountry());
        Glide.with(context).load(list.get(position).getFlag()).into(imageView);

        return view;
    }
}*/



































