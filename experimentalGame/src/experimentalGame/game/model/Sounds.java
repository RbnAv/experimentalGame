package experimentalGame.game.model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

	String explosion = "/experimentalGame/src/Sounds/explosion-1.wav";
	Media sonidoExplosionMedia;
	static MediaPlayer sonidoExplosionMPlayer;

	String ataque1 = "/experimentalGame/src/Sounds/explosion-1.wav";
	Media sonidoAtaque1Media;
	MediaPlayer sonidoAtaque1MPlayer;

	String ataque2 = "/experimentalGame/src/Sounds/explosion-1.wav";
	Media sonidoAtaque2Media;
	MediaPlayer sonidoAtaque2MPlayer;

	String curacion = "/experimentalGame/src/Sounds/explosion-1.wav";
	Media sonidoCuracionMedia;
	MediaPlayer sonidoCuracionMPlayer;
	
	public Sounds() {
		
	}

	public Sounds(String explosion, String ataque1, String ataque2, String curacion) {
		this.explosion = explosion;
		this.sonidoExplosionMedia = new Media(new File(explosion).toURI().toString());
		this.sonidoExplosionMPlayer = new MediaPlayer(sonidoExplosionMedia);
		
		this.ataque1 = ataque1;
		this.sonidoAtaque1Media = new Media(new File(ataque1).toURI().toString());
		this.sonidoAtaque1MPlayer = new MediaPlayer(sonidoAtaque1Media);
		
		this.ataque2 = ataque2;
		this.sonidoAtaque2Media = new Media(new File(ataque2).toURI().toString());
		this.sonidoAtaque2MPlayer = new MediaPlayer(sonidoAtaque2Media);
		
		this.curacion = curacion;
		this.sonidoCuracionMedia = new Media(new File(curacion).toURI().toString());
		this.sonidoCuracionMPlayer = new MediaPlayer(sonidoCuracionMedia);
	}

	public static void explosionPlay() {
		sonidoExplosionMPlayer.play();
	}

	public void explosionStop() {
		sonidoExplosionMPlayer.stop();
	}

	public void ataque1Play() {
		sonidoAtaque1MPlayer.play();
	}

	public void ataque1Stop() {
		sonidoAtaque1MPlayer.stop();
	}

	public void ataque2Play() {
		sonidoAtaque2MPlayer.play();
	}

	public void ataque2Stop() {
		sonidoAtaque2MPlayer.stop();
	}

	public void curacionPlay() {
		sonidoCuracionMPlayer.play();
	}

	public void curacionStop() {
		sonidoCuracionMPlayer.stop();
	}

	public String getExplosion() {
		return explosion;
	}

	public void setExplosion(String explosion) {
		this.explosion = explosion;
	}

	public Media getSonidoExplosionMedia() {
		return sonidoExplosionMedia;
	}

	public void setSonidoExplosionMedia(Media sonidoExplosionMedia) {
		this.sonidoExplosionMedia = sonidoExplosionMedia;
	}

	public MediaPlayer getSonidoExplosionMPlayer() {
		return sonidoExplosionMPlayer;
	}

	public void setSonidoExplosionMPlayer(MediaPlayer sonidoExplosionMPlayer) {
		this.sonidoExplosionMPlayer = sonidoExplosionMPlayer;
	}

	public String getAtaque1() {
		return ataque1;
	}

	public void setAtaque1(String ataque1) {
		this.ataque1 = ataque1;
	}

	public Media getSonidoAtaque1Media() {
		return sonidoAtaque1Media;
	}

	public void setSonidoAtaque1Media(Media sonidoAtaque1Media) {
		this.sonidoAtaque1Media = sonidoAtaque1Media;
	}

	public MediaPlayer getSonidoAtaque1MPlayer() {
		return sonidoAtaque1MPlayer;
	}

	public void setSonidoAtaque1MPlayer(MediaPlayer sonidoAtaque1MPlayer) {
		this.sonidoAtaque1MPlayer = sonidoAtaque1MPlayer;
	}

	public String getAtaque2() {
		return ataque2;
	}

	public void setAtaque2(String ataque2) {
		this.ataque2 = ataque2;
	}

	public Media getSonidoAtaque2Media() {
		return sonidoAtaque2Media;
	}

	public void setSonidoAtaque2Media(Media sonidoAtaque2Media) {
		this.sonidoAtaque2Media = sonidoAtaque2Media;
	}

	public MediaPlayer getSonidoAtaque2MPlayer() {
		return sonidoAtaque2MPlayer;
	}

	public void setSonidoAtaque2MPlayer(MediaPlayer sonidoAtaque2MPlayer) {
		this.sonidoAtaque2MPlayer = sonidoAtaque2MPlayer;
	}

	public String getCuracion() {
		return curacion;
	}

	public void setCuracion(String curacion) {
		this.curacion = curacion;
	}

	public Media getSonidoCuracionMedia() {
		return sonidoCuracionMedia;
	}

	public void setSonidoCuracionMedia(Media sonidoCuracionMedia) {
		this.sonidoCuracionMedia = sonidoCuracionMedia;
	}

	public MediaPlayer getSonidoCuracionMPlayer() {
		return sonidoCuracionMPlayer;
	}

	public void setSonidoCuracionMPlayer(MediaPlayer sonidoCuracionMPlayer) {
		this.sonidoCuracionMPlayer = sonidoCuracionMPlayer;
	}
}







