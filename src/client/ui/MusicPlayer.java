package client.ui;

import client.controllers.PlayerController;
import entities.Playlist;
import entities.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class MusicPlayer
{
    private static ListView<Playlist> playlists;
    private static ListView<Song> playlistSongs;
    private static ListView<Song> songList;
    private static PlayerController controller;
    private static List<Song> Songs;
    private static int songListPage = 0;
    static final int pageSize = 5;
    private static Text pageNumber;

    public static BorderPane pane(PlayerController playerController)
    {
        controller = playerController;
        return new BorderPane();
    }

    public static void form(BorderPane pane)
    {
        TextField searchField = new TextField();
        searchField.setPrefSize(400,40);

        List<String> searchOptions = Arrays.asList("Artist", "Song", "Genre");
        ComboBox<String> searchCombo = new ComboBox<>(
                FXCollections.observableArrayList(searchOptions));
        searchCombo.getSelectionModel().select(1);
        

        Button search = new Button("Search");
        search.setPrefSize(100, 40);
        search.setDefaultButton(true);
        search.setOnAction(actionEvent ->
                controller.search(searchField.getText(), searchCombo.getValue()));

        HBox top =  new HBox(5);
        top.getChildren().addAll(searchField, searchCombo, search);
        pane.setTop(top);

        Label playlistslabel = new Label("Playlists");
        TextField playlistName = new TextField();
        playlistName.setPrefHeight(40);
        Button addPlaylist = new Button("Add");
        addPlaylist.setPrefSize(100, 40);
        addPlaylist.setDefaultButton(true);
        addPlaylist.setOnAction(actionEvent ->
                controller.createPlaylist(playlistName.getText()));
        HBox playBox = new HBox(playlistName, addPlaylist);
        
        Button prevPageButton = new Button("<-");
        prevPageButton.setPrefSize(40,40);
        prevPageButton.setDefaultButton(true);
        prevPageButton.setOnAction(actionEvent -> prevPage());
        Button nextPageButton = new Button("->");        
        nextPageButton.setPrefSize(40, 40);
        nextPageButton.setDefaultButton(true);
        nextPageButton.setOnAction(actionEvent -> nextPage());
        pageNumber = new Text("page " + (songListPage+1));
        HBox pageBox = new HBox(prevPageButton, pageNumber, nextPageButton);
        pageBox.setAlignment(Pos.CENTER);
        pageBox.setSpacing(10);
        pane.setBottom(pageBox);

        playlists = new ListView<>();
        playlists.setCellFactory(param -> new PlayItem());
        Label playlistSongsLabel = new Label("Songs");
        playlistSongs = new ListView<>();
        playlistSongs.setCellFactory(param -> new SongItem());
        VBox side = new VBox(
                playlistslabel, playBox, playlists, playlistSongsLabel, playlistSongs);
        pane.setLeft(side);

        songList = new ListView<>();
        songList.setCellFactory(param -> new Row());
        pane.setCenter(songList);
    }
    
    public static void prevPage()
    {
    	if(songListPage > 0)
    		songListPage--;
    	setsongList();
    }
    
    public static void nextPage()
    {
    	if(songListPage < Songs.size()/pageSize)
    		songListPage++;
    	setsongList();
    }
    
    public static void setsongList()
    {
        songList.getItems().clear();
        int fromIndex = pageSize * songListPage;
        pageNumber.setText("page " + (songListPage + 1));
        int toIndex = pageSize * (songListPage+1);
        if(pageSize * (songListPage+1) > Songs.size())
        	toIndex = Songs.size();
        ObservableList<Song> obsSongs = FXCollections.observableArrayList(Songs.subList(fromIndex, toIndex));
        songList.setItems(obsSongs);
    }

    public static void searchResults(List<Song> songs)
    {
    	Songs = songs;
    	setsongList();
    }

    public static void displayPlaylists(List<Playlist> playlist)
    {
        playlists.getItems().clear();
        ObservableList<Playlist> obsPlaylist = FXCollections.observableArrayList(playlist);
        playlists.setItems(obsPlaylist);
    }

    public static void displayPlaylistSongs(List<Song> songs)
    {
        playlistSongs.getItems().clear();
        ObservableList<Song> obsSongs = FXCollections.observableArrayList(songs);
        playlistSongs.setItems(obsSongs);
    }

    public static class Row extends ListCell<Song>
    {
        private HBox hbox;
        private Pane pane;
        private Button add;
        private Button play;
        private Text title;
        private boolean playing;

        public Row()
        {
            super();
            pane = new Pane();
            add = new Button("+");
            add.setPrefSize(40, 40);
            add.setDefaultButton(true);
            play = new Button("play");
            play.setPrefSize(80, 40);
            play.setDefaultButton(true);
            title = new Text();
            hbox = new HBox(add, play, title);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(Song song, boolean empty)
        {
            super.updateItem(song, empty);

            if(song == null || empty)
                setGraphic(null);
            else{
                add.setOnAction(event ->
                        controller.addToPlaylist(
                                playlists.getSelectionModel().getSelectedItem().getName(),
                                song.getId(),
                                song.getTitle()));
               play.setOnAction(event -> {
                    if(playing){
                        play.setText("play");
                        controller.mp3stop();
                    }
                    else{
                        play.setText("stop");
                        controller.mp3play();
                    }
                    playing = !playing;
                });
                title.setText(song.getTitle());
                setGraphic(hbox);
            }
        }
    }

    public static class PlayItem extends ListCell<Playlist>
    {
        private HBox hbox;
        private Text name;
        private Button delete;

        public PlayItem()
        {
            name = new Text();
            delete = new Button("del");
            delete.setPrefSize(60, 20);
            delete.setDefaultButton(true);
            hbox = new HBox(name, delete);
            hbox.setSpacing(10);

        }

        @Override
        protected void updateItem(Playlist playlist, boolean empty)
        {
            super.updateItem(playlist, empty);

            if(playlist == null || empty)
                setGraphic(null);
            else{
                setOnMouseClicked(event -> {
                    playlistSongs.getItems().clear();
                    displayPlaylistSongs(playlist.getSongs());

                });
                delete.setOnAction(param -> controller.deletePlaylist(playlist.getName()));
                name.setText(playlist.getName());
                setGraphic(hbox);
            }
        }

    }


    public static class SongItem extends ListCell<Song>
    {
        private Text title;

        public SongItem()
        {
            title = new Text();
        }

        @Override
        protected void updateItem(Song song, boolean empty)
        {
            super.updateItem(song, empty);

            if(song == null || empty)
                setGraphic(null);
            else{
                title.setText(song.getTitle());
                setGraphic(title);
            }
        }

    }
}
