package fr.android.paintersimon.presentation.Library
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.android.paintersimon.R
import fr.android.paintersimon.domain.Book


class LibraryAdapter(private var mList: List<Book>) : RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {


        private lateinit var mListener : onItemCLickListener

        interface onItemCLickListener{
            fun onItemClick(position : Int)
        }

        fun setOnItemCLickLIstener(listener: onItemCLickListener){
            mListener = listener
        }


        // create new views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflates the card_view_design view
            // that is used to hold list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_design, parent, false)

            return ViewHolder(view, mListener)
        }

        // binds the list items to a view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val ItemsViewModel = mList[position]

            // sets the text to the textview from our itemHolder class
            holder.textView.text = mList.get(position).title
            Picasso.get().load(mList.get(position).cover).into(holder.imageView);

        }

        // return the number of the items in the list
        override fun getItemCount(): Int {
            return mList.size
        }

        // Holds the views for adding it to image and text
        class ViewHolder(ItemView: View, listener: onItemCLickListener) : RecyclerView.ViewHolder(ItemView) {
            val textView: TextView = itemView.findViewById(R.id.textView)
            val imageView: ImageView = itemView.findViewById(R.id.imageview)

            init {
                itemView.setOnClickListener{
                    listener.onItemClick(bindingAdapterPosition);
                }
            }
        }
        fun setList(booksP:List<Book> ){
            mList = booksP
        }

    }