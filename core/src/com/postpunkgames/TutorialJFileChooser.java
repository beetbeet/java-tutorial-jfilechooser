package com.postpunkgames;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TutorialJFileChooser extends ApplicationAdapter {

	private String filePath = "";
	private Texture texture;
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		keyinput();

		if (filePath != "") {

			batch.begin();
			batch.draw(texture, 0, 0);
			batch.end();

		}
	}

	public void keyinput() {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {

			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {

					JFileChooser chooser = new JFileChooser();
					FileFilter filter = new FileNameExtensionFilter("jpg or png file",
							new String[] { "jpg", "png" });
					chooser.setFileFilter(filter);
					chooser.addChoosableFileFilter(filter);
					int returnVal = chooser.showOpenDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						filePath = chooser.getSelectedFile().getAbsolutePath();
					}
					Gdx.app.postRunnable(new Runnable() {

						@Override
						public void run() {

							if (filePath != "") {

								texture = new Texture(filePath);
								System.out.println("selected file is " + filePath);

							}

						}
					});
				}
			});

		}
	}

}
