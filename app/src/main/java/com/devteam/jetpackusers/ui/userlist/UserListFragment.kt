package com.devteam.jetpackusers.ui.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.devteam.jetpackusers.R
import com.devteam.jetpackusers.common.loadImageByUrl
import com.devteam.jetpackusers.databinding.UserListFragmentBinding
import com.devteam.jetpackusers.databinding.UserListItemBinding
import com.devteam.jetpackusers.dummy.DummyContent
import com.devteam.jetpackusers.io.model.User
import com.devteam.jetpackusers.utils.Logger

class UserListFragment : Fragment() {
    lateinit var binding: UserListFragmentBinding
    private lateinit var viewModel: UserListViewModel
    private val adapter = UserListRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_list_fragment, container, false)
        binding = UserListFragmentBinding.inflate(inflater, view as ViewGroup?, false);

        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        initAdapter()
    }

    private fun initAdapter() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)
        binding.list.layoutManager = LinearLayoutManager(context);

        // get the users for page
        viewModel.users.observe(viewLifecycleOwner, Observer<List<User>> {
            Log.d("Activity", "*** list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        // TODO: This api needs to be called from User Details screen
        // get the user details
        viewModel.userDetails.observe(viewLifecycleOwner, Observer {
            Log.d("Activity", "*** User Details: ${it}")
        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.list.visibility = View.VISIBLE
        }
    }
}


class UserListRecyclerViewAdapter :
    ListAdapter<User, UserListRecyclerViewAdapter.ViewHolder>(REPO_COMPARATOR),
    UserListClickListner {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        var adapterBinding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            view as ViewGroup?,
            false
        );

        return ViewHolder(adapterBinding.root, adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        Logger.d("**** onBindViewHolder - $user")
        holder.adapterBinding.userAvatar.loadImageByUrl(user.avatar)
        holder.adapterBinding.user = user
        holder.adapterBinding.callback = this
    }


    inner class ViewHolder(val view: View, adapterBinding: UserListItemBinding) :
        RecyclerView.ViewHolder(view) {
        var adapterBinding: UserListItemBinding = adapterBinding

    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }

    override fun onUserClicked(id: Int) {
    }
}