package com.burakeregar.demo.viewholder

import android.view.View
import com.burakeregar.demo.model.ContactModel
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import kotlinx.android.synthetic.main.row_contact.view.*

class ContactViewHolder(itemView: View) : GenericViewHolder<ContactModel>(itemView) {

    override fun bindData(element: ContactModel) {
        itemView.rowName.text = element.surname
        itemView.rowSurname.text = element.name
    }
}