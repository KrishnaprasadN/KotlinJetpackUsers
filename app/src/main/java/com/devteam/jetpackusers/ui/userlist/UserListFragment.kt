package com.devteam.jetpackusers.ui.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devteam.jetpackusers.R
import com.devteam.jetpackusers.databinding.UserListFragmentBinding
import com.devteam.jetpackusers.databinding.UserListItemBinding
import com.devteam.jetpackusers.dummy.DummyContent

class UserListFragment : Fragment() {
    lateinit var  binding : UserListFragmentBinding

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_list_fragment, container, false)
        binding = UserListFragmentBinding.inflate(inflater, view as ViewGroup?,false);

        binding.list.layoutManager = LinearLayoutManager(context);
        binding.list.adapter =
            UserListRecyclerViewAdapter(
                DummyContent.ITEMS
            )
        return  binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
    }
}


class UserListRecyclerViewAdapter(
        private val values: List<DummyContent.DummyItem>
) : RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item, parent, false)
        var adapterBinding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), view as ViewGroup?,false);

        return ViewHolder(adapterBinding.root,adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.adapterBinding.userId.text = item.id
        holder.adapterBinding.userName.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View, adapterBinding: UserListItemBinding) : RecyclerView.ViewHolder(view) {
         var adapterBinding : UserListItemBinding = adapterBinding
    }
}