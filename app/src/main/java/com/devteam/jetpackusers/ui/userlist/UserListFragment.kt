package com.devteam.jetpackusers.ui.userlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.devteam.jetpackusers.R
import com.devteam.jetpackusers.common.loadImageByUrl
import com.devteam.jetpackusers.databinding.FragmentUserListBinding
import com.devteam.jetpackusers.databinding.ItemUserListBinding
import com.devteam.jetpackusers.io.model.User

class UserListFragment : Fragment() {
    lateinit var binding: FragmentUserListBinding
    private lateinit var viewModel: UserListViewModel
    private val adapter = UserListRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        binding = FragmentUserListBinding.inflate(inflater, view as ViewGroup?, false);

        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        initAdapter()
    }

    private fun initAdapter() {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.list.adapter = adapter
        binding.list.addItemDecoration(decoration)
        binding.list.layoutManager = LinearLayoutManager(context);


        // get the users for page
        viewModel.users.observe(viewLifecycleOwner, Observer<List<User>> {
            Log.d("Activity", "*** list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
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


class UserListRecyclerViewAdapter(private val fragment: Fragment) :
    ListAdapter<User, UserListRecyclerViewAdapter.ViewHolder>(REPO_COMPARATOR),
    UserListClickListner {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_list, parent, false)
        var adapterBinding = ItemUserListBinding.inflate(
            LayoutInflater.from(parent.context),
            view as ViewGroup?,
            false
        );

        return ViewHolder(adapterBinding.root, adapterBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.adapterBinding.user = user
        holder.adapterBinding.callback = this
    }

    inner class ViewHolder(val view: View, adapterBinding: ItemUserListBinding) :
        RecyclerView.ViewHolder(view) {
        var adapterBinding: ItemUserListBinding = adapterBinding

    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
    override fun onUserClicked(view : View, userId : Integer) {
        var bundle = bundleOf("userId" to userId)
        view.findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment,bundle)

    }
}