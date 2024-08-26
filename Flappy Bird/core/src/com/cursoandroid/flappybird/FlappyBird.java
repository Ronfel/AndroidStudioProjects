package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Texture canoTopo;
    private Texture canoBaixo;
    private Texture gameOver;
    private Random numRandomico;
    private BitmapFont fonte;
    private BitmapFont Messagem;
    private Circle passaroCirulo;
    private Rectangle cTopo;
    private Rectangle cBaixo;
    private ShapeRenderer shapeRenderer;

    private float variacao = 0;

    private int largDispositivo;
    private int altDispositivo;
    private int estadoJogo = 0;
    private int pontuacao = 0;

    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovHor = 0;
    private float espEntCanos = 400;
    private float deltaTime;
    private float altEntCanRandom;
    private boolean marcou = false;

    private OrthographicCamera orthographicCamera;
    private Viewport viewport;

    private final float VIRTUAL_WIDTH = 720;
    private final float VIRTUAL_HEIGHT = 1280;


    @Override
    public void create() {
        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(10);

        Messagem = new BitmapFont();
        Messagem.setColor(Color.WHITE);
        Messagem.getData().setScale(3);

        passaroCirulo = new Circle();
        cBaixo = new Rectangle();
        cTopo = new Rectangle();
        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();
        passaro = new Texture[3];
        numRandomico = new Random();
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");
        fundo = new Texture("fundo.png");
        canoTopo = new Texture("cano_topo_maior.png");
        canoBaixo = new Texture("cano_baixo_maior.png");
        gameOver = new Texture("game_over.png");

        orthographicCamera = new OrthographicCamera();
        orthographicCamera.position.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2, 0);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, orthographicCamera);

        largDispositivo = (int) VIRTUAL_WIDTH;
        altDispositivo = (int) VIRTUAL_HEIGHT;
        posicaoInicialVertical = altDispositivo / 2;
        posicaoMovHor = largDispositivo;


    }

    @Override
    public void render() {
        orthographicCamera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 5;

        if (variacao > 2)
            variacao = 0;

        if (estadoJogo == 0) {
            if (Gdx.input.justTouched())
                estadoJogo = 1;
        } else {
            velocidadeQueda++;
            if (posicaoInicialVertical > 0)
                posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;

            if (estadoJogo == 1) {
                posicaoMovHor -= deltaTime * 500;
                if (Gdx.input.justTouched()) {
                    velocidadeQueda = -20;
                }

                if (posicaoMovHor < -canoBaixo.getWidth()) {
                    posicaoMovHor = largDispositivo;
                    altEntCanRandom = numRandomico.nextInt(700) - 350;
                    marcou = false;
                }

                if (posicaoMovHor < 200) {
                    if (!marcou) {
                        pontuacao++;
                        marcou = true;
                    }
                }
            } else {
                if (Gdx.input.justTouched()) {
                    estadoJogo = 0;
                    velocidadeQueda = 0;
                    posicaoInicialVertical = altDispositivo / 2;
                    posicaoMovHor = largDispositivo;
                    pontuacao = 0;
                    marcou = false;
                }
            }
        }
        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();

        batch.draw(fundo, 0, 0, largDispositivo, altDispositivo);

        batch.draw(canoTopo, posicaoMovHor, altDispositivo / 2 + espEntCanos / 2 + altEntCanRandom);
        batch.draw(canoBaixo, posicaoMovHor, altDispositivo / 2 - canoBaixo.getHeight() - espEntCanos / 2 + altEntCanRandom);
        batch.draw(passaro[(int) variacao], 200, posicaoInicialVertical);

        fonte.draw(batch, String.valueOf(pontuacao), largDispositivo / 2, altDispositivo - 50);
        if (estadoJogo == 2) {
            batch.draw(gameOver, largDispositivo / 2 - gameOver.getWidth() / 2, altDispositivo / 2);
            Messagem.draw(batch, "Toque na tela para reiniciar", largDispositivo / 2 - gameOver.getWidth() / 2 - 75, altDispositivo / 2);
        }
        batch.end();

        passaroCirulo.set(235, posicaoInicialVertical + 20, 30);

        cTopo.set(posicaoMovHor, altDispositivo / 2 + espEntCanos / 2 + altEntCanRandom, canoTopo.getWidth(), canoTopo.getHeight());
        cBaixo.set(posicaoMovHor, altDispositivo / 2 - canoBaixo.getHeight() - espEntCanos / 2 + altEntCanRandom, canoBaixo.getWidth(), canoBaixo.getHeight());

        //   shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //   shapeRenderer.circle(passaroCirulo.x, passaroCirulo.y, passaroCirulo.radius);
        //   shapeRenderer.rect(cTopo.x, cTopo.y, cTopo.getWidth(), cTopo.getHeight());
        //   shapeRenderer.rect(cBaixo.x, cBaixo.y, cBaixo.getWidth(), cBaixo.getHeight());
        //
        //   shapeRenderer.setColor(Color.RED);
        //   shapeRenderer.end();

        if ((Intersector.overlaps(passaroCirulo, cBaixo)) || (Intersector.overlaps(passaroCirulo, cTopo))
                || (posicaoInicialVertical <= 0)|| (posicaoInicialVertical >= altDispositivo) ) {
            estadoJogo = 2;
        }
    }
    @Override
    public void resize(int width, int height){
       viewport.update(width, height);
    }
}
