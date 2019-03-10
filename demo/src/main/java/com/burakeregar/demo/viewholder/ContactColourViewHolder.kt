package com.burakeregar.demo.viewholder

import android.view.View
import com.burakeregar.demo.model.ContactColourModel
import com.burakeregar.easiestgenericrecycleradapter.base.GenericViewHolder
import kotlinx.android.synthetic.main.row_contact.view.*

class ContactColourViewHolder(itemView: View) : GenericViewHolder<ContactColourModel>(itemView) {

    override fun bindData(element: ContactColourModel) {
        itemView.rowName.text = element.surname
        itemView.rowSurname.text = element.name
        itemView.contactRow.setBackgroundColor(element.colour)
    }
}