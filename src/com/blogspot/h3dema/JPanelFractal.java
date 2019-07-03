/*
 * fractal apresentado em Java como programar 8ª edição
 * Paul Deitel e Harvey Deitel
 * seção 15.9
 */

package com.blogspot.h3dema;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Henrique
 */
public class JPanelFractal extends javax.swing.JPanel {

    public static final int MIN_NIVEL = 0;
    public static final int MAX_NIVEL = 15;
        
    private Color cor;
    private int nivel = MIN_NIVEL;
    
    private int numLinhas;
    
    /**
     * Creates new form JPanelFractal
     * @param nivelInicial indica o nivel inicial do fractal
     */
    public JPanelFractal(int nivelInicial) {                
        numLinhas = 3;
        cor = Color.BLACK;
        setNivel(nivelInicial);
        setBackground( Color.WHITE );        
    }

    /**
     * usado para desenhar o fractal de forma recursiva
     * @param nivel
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param g 
     */
    private void desenha(int nivel, int x1, int y1, int x2, int y2, Graphics g) {
        if (0 == nivel) {
            g.drawLine(x1, y1, x2, y2);
        } else {
            /**
             * calcula o ponto médio entre as entradas
             */
            int x3 = (x1 + x2) / 2;
            int y3 = (y1 + y2) / 2;
            /**
             * calcula um ponto para formação de um triângulo isósceles reto
             */
            int x4 = x1 + (x3 - x1) / 2 - (y3 - y1) / 2;
            int y4 = y1 + (y3 - y1) / 2 + (x3 - x1) / 2;
            /**
             * desenha o fractal com 3 chamadas recursivas
             */
            desenha(nivel - 1, x4, y4, x1, y1, g);
            desenha(nivel - 1, x4, y4, x3, y3, g);
            desenha(nivel - 1, x4, y4, x2, y2, g);
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // desenha o fractal
        g.setColor( cor );
        g.drawString("processando..", this.getWidth() - 100, this.getHeight());
        switch (numLinhas){
            case 1:
            case 2:
                desenha(nivel, 100, 90, Math.max(290, 2 * this.getWidth() / 3), Math.max(200, this.getHeight() / 2), g);
                break;
            case 3:
                int x1 = Math.max(40, this.getWidth() / 3);
                int y1 = Math.max(40, this.getHeight() / 4);
                int x2 = Math.max(290, 4 * this.getWidth() / 5);
                int y2 = Math.max(200, 3 * this.getHeight() / 4);
                int x3 = (x2 + x1) / 2;
                int y3 = (y2 + y1) / 2;
                desenha(nivel, x3, y1, x3, y3, g);
                desenha(nivel, x3, y3, x1, y2, g);
                desenha(nivel, x3, y3, x2, y2, g);
                break;
        }
        g.setColor( Color.WHITE );
        g.fillRect(this.getWidth() - 100, this.getHeight()-10, this.getWidth(), this.getHeight());
    }
    
    /**
     * @return the cor
     */
    public Color getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * permite atualizar o nivel, verificando os limites<br/>
     * se fora dos limites, não atualiza
     * @param nivel the nivel to set
     */
    public final void setNivel(int nivel) {
        if ((nivel >= MIN_NIVEL) && (nivel <= MAX_NIVEL))
            this.nivel = nivel;
    }
}
