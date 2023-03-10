package fr.android.paintersimon.presentation.Panier
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book
import fr.android.paintersimon.domain.SousPanier


class PanierAdapter(private var mList: List<SousPanier>) : RecyclerView.Adapter<PanierAdapter.ViewHolder>() {



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_panier, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = mList.get(position).book.title
        Picasso.get().load(mList.get(position).book.cover).into(holder.imageView);
        holder.quantityTextView.text = "Quantity: "+mList.get(position).quantity.toString()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity)
    }
    fun setList(booksP: List<SousPanier>){
        mList = booksP
    }

}