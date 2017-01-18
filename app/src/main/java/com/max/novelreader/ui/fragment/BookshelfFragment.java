package com.max.novelreader.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.max.novelreader.R;
import com.max.novelreader.adapter.BookshelfAdapter;
import com.max.novelreader.bean.Book;
import com.max.novelreader.di.components.BookshelfComponent;
import com.max.novelreader.di.components.DaggerBookshelfComponent;
import com.max.novelreader.di.modules.BookshelfModule;
import com.max.novelreader.event.HideBsDelBtnEvent;
import com.max.novelreader.event.ShowBsDelBtnEvent;
import com.max.novelreader.event.UpdateBsDelNumEvent;
import com.max.novelreader.mvp.presenter.BookshelfPresenter;
import com.max.novelreader.mvp.view.BookshelfFragmentView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookshelfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookshelfFragment extends Fragment implements BookshelfFragmentView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_menu_cancel)
    TextView tvMenuCancel;

    @Inject
    BookshelfPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
//    @BindView(R.id.btn_bs_del)
//    Button btnDel;

    BookshelfAdapter bookshelfAdapter;
    Menu mMenu;

    private BookshelfComponent bookshelfComponent;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BookshelfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookshelfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookshelfFragment newInstance(String param1, String param2) {
        BookshelfFragment fragment = new BookshelfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);

        bookshelfComponent = DaggerBookshelfComponent.builder().bookshelfModule(new BookshelfModule()).build();
        bookshelfComponent.inject(this);

        presenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookshelf, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initView();

        presenter.onCreate();

    }

    private void initView() {
        tvTitleBar.setText(R.string.bookshelf);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        tvMenuCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancelEditMode(getActivity());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        presenter.onPrepareMenu(menu);
        mMenu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.onMenuItemSelect(getActivity(), item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showEditMode(Menu menu) {
        tvMenuCancel.setVisibility(View.VISIBLE);
        MenuItem edit = menu.findItem(R.id.menu_edit);
        MenuItem select = menu.findItem(R.id.menu_select_all);
        edit.setVisible(false);
        select.setVisible(true);

        if (bookshelfAdapter != null) {
            bookshelfAdapter.setEditMode(true);
            bookshelfAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void hideEditMode(Menu menu) {
        tvMenuCancel.setVisibility(View.GONE);
        MenuItem edit = menu.findItem(R.id.menu_edit);
        MenuItem select = menu.findItem(R.id.menu_select_all);
        edit.setVisible(true);
        select.setVisible(false);

        if (bookshelfAdapter != null) {
            bookshelfAdapter.setEditMode(false);
            bookshelfAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void refreshBookShelf(List<Book> bookList) {
        if (bookshelfAdapter == null) {
            bookshelfAdapter = new BookshelfAdapter(getContext(), bookList, presenter);
            recyclerView.setAdapter(bookshelfAdapter);
        } else {
            bookshelfAdapter.setBookList(bookList);
            bookshelfAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setBookShelfEmpty() {

    }

    @Override
    public void showMenuSelectAll() {
        MenuItem menuItem = mMenu.findItem(R.id.menu_select_all);
        if (menuItem != null) {
            menuItem.setTitle(getString(R.string.menu_select_all));
        }
    }

    @Override
    public void showMenuUnselectAll() {
        MenuItem menuItem = mMenu.findItem(R.id.menu_select_all);
        if (menuItem != null) {
            menuItem.setTitle(getString(R.string.menu_cancel_all));
        }
    }

    @Override
    public void showDelBtn() {

        EventBus.getDefault().post(new ShowBsDelBtnEvent());
    }

    @Override
    public void hideDelBtn() {

        EventBus.getDefault().post(new HideBsDelBtnEvent());

    }

    @Override
    public void refreshSelectView(int selectCount) {
        if(bookshelfAdapter != null) {
            bookshelfAdapter.notifyDataSetChanged();
        }
        EventBus.getDefault().post(new UpdateBsDelNumEvent(selectCount));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Bundle bundle);
    }
}
