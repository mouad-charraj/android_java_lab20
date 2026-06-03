package com.example.lab20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter_mouad extends RecyclerView.Adapter<ContactAdapter_mouad.ContactViewHolder_mouad> {

    private List<Contact_mouad> contacts_mouad;

    public ContactAdapter_mouad(List<Contact_mouad> contacts_mouad) {
        this.contacts_mouad = contacts_mouad;
    }

    @NonNull
    @Override
    public ContactViewHolder_mouad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_mouad = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact_mouad, parent, false);
        return new ContactViewHolder_mouad(view_mouad);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder_mouad holder_mouad, int position) {
        Contact_mouad contact_mouad = contacts_mouad.get(position);
        holder_mouad.tvName_mouad.setText(contact_mouad.getName_mouad());
        holder_mouad.tvPhone_mouad.setText(contact_mouad.getPhone_mouad());
    }

    @Override
    public int getItemCount() {
        return contacts_mouad.size();
    }

    public void updateData_mouad(List<Contact_mouad> newContacts_mouad) {
        this.contacts_mouad = newContacts_mouad;
        notifyDataSetChanged();
    }

    static class ContactViewHolder_mouad extends RecyclerView.ViewHolder {
        TextView tvName_mouad, tvPhone_mouad;

        public ContactViewHolder_mouad(@NonNull View itemView) {
            super(itemView);
            tvName_mouad = itemView.findViewById(R.id.tvName_mouad);
            tvPhone_mouad = itemView.findViewById(R.id.tvPhone_mouad);
        }
    }
}
