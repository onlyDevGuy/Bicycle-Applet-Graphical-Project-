package vut;

/*
 * Testing coordinates used:
 * x = 90, y = 200
 * x = 120, y = 210
 * x = 150, y = 180
 * 
 * Bike dimensions are scaled to fit within 400x400 pixels
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class Bicycle {

    private int x;                  
    private int y;                  
    private Color bodyColor;       
    private final double scale = 0.75;   

    public Bicycle() {
        this.x = 90;
        this.y = 200;
        this.bodyColor = Color.RED;
    }

    public Bicycle(int x, int y) {
        this.x = x;
        this.y = y;
        this.bodyColor = Color.RED;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Color getBodyColor() {
        return bodyColor;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public void drawBike(Graphics2D g2) {
        setQuality(g2);

        int rearCx = tx(0);
        int rearCy = ty(0);

        int frontCx = tx(332);
        int frontCy = ty(0);

        int seatJointX = tx(119);
        int seatJointY = ty(-103);

        int headTopX = tx(277);
        int headTopY = ty(-148);

        int topTubeMeetingX = tx(262);
        int topTubeMeetingY = ty(-103);

        int forkMeetingX = tx(283);
        int forkMeetingY = ty(-127);

        int bbX = tx(163);          
        int bbY = ty(1);

        int seatTopX = tx(107);      
        int seatTopY = ty(-136);

        backWheelDraw(g2, rearCx, rearCy);
        frontWheelDraw(g2, frontCx, frontCy);

        bodyDraw(g2,
                rearCx, rearCy,
                seatJointX, seatJointY,
                headTopX, headTopY,
                topTubeMeetingX, topTubeMeetingY,
                forkMeetingX, forkMeetingY,
                bbX, bbY);

        seatPostDraw(g2, seatJointX, seatJointY, seatTopX, seatTopY);
        saddleDraw(g2, seatTopX, seatTopY);
        handleGripDraw(g2, headTopX, headTopY);
        bikePaddleDraw(g2, bbX, bbY);
        frontForkDraw(g2, frontCx, frontCy, headTopX, headTopY);
    }

    private void setQuality(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
    }

    public void bodyDraw(Graphics2D g2,
                         int rearCx, int rearCy,
                         int seatJointX, int seatJointY,
                         int headTopX, int headTopY,
                         int topTubeMeetingX, int topTubeMeetingY,
                         int forkMeetingX, int forkMeetingY,
                         int bbX, int bbY) {

        g2.setColor(bodyColor);

        g2.setStroke(new BasicStroke(sw(16f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        double dx2 = forkMeetingX - bbX;
        double dy2 = forkMeetingY - bbY;
        double length = Math.sqrt(dx2 * dx2 + dy2 * dy2);

        double extendX = (dx2 / length) * sw(8);
        double extendY = (dy2 / length) * sw(8);

        int extendedX = (int) Math.round(forkMeetingX + extendX);
        int extendedY = (int) Math.round(forkMeetingY + extendY);

        g2.drawLine(bbX, bbY, extendedX, extendedY);

        g2.setStroke(new BasicStroke(sw(8f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2.drawLine(seatJointX, seatJointY, topTubeMeetingX, topTubeMeetingY);

        g2.drawLine(seatJointX, seatJointY, bbX, bbY);

        int hubR = s(20);
        int outlineStrokeWidth = sw(2);
        int outlineOffset = outlineStrokeWidth / 2;

        double t = 0.55;
        int seatTubePointX = (int) Math.round(seatJointX + t * (bbX - seatJointX));
        int seatTubePointY = (int) Math.round(seatJointY + t * (bbY - seatJointY));

        double dx = seatTubePointX - rearCx;
        double dy = seatTubePointY - rearCy;
        double distance = Math.sqrt(dx * dx + dy * dy);

        double unitX = dx / distance;
        double unitY = dy / distance;

        int seatStayStartX = (int) Math.round(rearCx + unitX * (hubR + outlineOffset));
        int seatStayStartY = (int) Math.round(rearCy + unitY * (hubR + outlineOffset));

        g2.drawLine(seatStayStartX, seatStayStartY, seatTubePointX, seatTubePointY);

        double dxChain = (bbX - sw(6)) - rearCx;
        double dyChain = (bbY + sw(1)) - rearCy;
        double distanceChain = Math.sqrt(dxChain * dxChain + dyChain * dyChain);

        double unitXChain = dxChain / distanceChain;
        double unitYChain = dyChain / distanceChain;

        int chainStayStartX = (int) Math.round(rearCx + unitXChain * (hubR + outlineOffset));
        int chainStayStartY = (int) Math.round(rearCy + unitYChain * (hubR + outlineOffset));

        g2.drawLine(chainStayStartX, chainStayStartY, bbX - sw(6), bbY + sw(1));
    }

    public void seatPostDraw(Graphics2D g2, int seatJointX, int seatJointY, int seatTopX, int seatTopY) {
        g2.setColor(bodyColor);
        g2.setStroke(new BasicStroke(sw(6f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(seatJointX, seatJointY, seatTopX, seatTopY);
    }

    public void saddleDraw(Graphics2D g2, int seatTopX, int seatTopY) {
        g2.setColor(Color.BLUE);

        GeneralPath saddle = new GeneralPath();
        saddle.moveTo(seatTopX - s(34), seatTopY - s(5));
        saddle.curveTo(seatTopX - s(22), seatTopY - s(14),
                seatTopX + s(5), seatTopY - s(14),
                seatTopX + s(19), seatTopY - s(8));
        saddle.curveTo(seatTopX + s(10), seatTopY + s(2),
                seatTopX - s(6), seatTopY + s(3),
                seatTopX - s(22), seatTopY + s(2));
        saddle.curveTo(seatTopX - s(31), seatTopY + s(1),
                seatTopX - s(38), seatTopY - s(2),
                seatTopX - s(34), seatTopY - s(5));
        saddle.closePath();

        g2.fill(saddle);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(sw(2f)));
        g2.draw(saddle);
    }

    public void handleGripDraw(Graphics2D g2, int headTopX, int headTopY) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(sw(7f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int stemTopX = headTopX + s(9);
        int stemTopY = headTopY - s(18);

        int leftTopX = stemTopX - s(20);
        int leftTopY = stemTopY - s(10);

        int leftBottomX = stemTopX - s(33);
        int leftBottomY = stemTopY + s(5);

        int rightGripX = stemTopX + s(15);
        int rightGripY = stemTopY + s(2);

        g2.drawLine(headTopX, headTopY, stemTopX, stemTopY);

        g2.drawLine(stemTopX, stemTopY, leftTopX, leftTopY);
        g2.drawLine(leftTopX, leftTopY, leftBottomX, leftBottomY);

        g2.drawLine(stemTopX, stemTopY, rightGripX, rightGripY);
    }

    public void frontWheelDraw(Graphics2D g2, int cx, int cy) {
        drawWheel(g2, cx, cy);
    }

    public void backWheelDraw(Graphics2D g2, int cx, int cy) {
        drawWheel(g2, cx, cy);
    }

    public void bikePaddleDraw(Graphics2D g2, int bbX, int bbY) {
        int r = s(22);

        g2.setColor(new Color(85, 85, 85));
        g2.fillOval(bbX - r, bbY - r, r * 2, r * 2);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(sw(3f)));
        g2.drawOval(bbX - r, bbY - r, r * 2, r * 2);

        g2.setStroke(new BasicStroke(sw(6f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(bbX + s(6), bbY + s(4), bbX + s(30), bbY + s(16));

        g2.setStroke(new BasicStroke(sw(5f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(bbX + s(30), bbY + s(16), bbX + s(50), bbY + s(16));
    }

    private void frontForkDraw(Graphics2D g2, int frontCx, int frontCy, int headTopX, int headTopY) {
        g2.setColor(bodyColor);
        g2.setStroke(new BasicStroke(sw(8f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int hubR = s(20);
        int outlineStrokeWidth = sw(2);
        int outlineOffset = outlineStrokeWidth / 2;

        int handlebarX = headTopX + s(9);
        int handlebarY = headTopY - s(18);

        double dx = handlebarX - frontCx;
        double dy = handlebarY - frontCy;
        double distance = Math.sqrt(dx * dx + dy * dy);

        double unitX = dx / distance;
        double unitY = dy / distance;

        int forkStartX = (int) Math.round(frontCx + unitX * (hubR + outlineOffset));
        int forkStartY = (int) Math.round(frontCy + unitY * (hubR + outlineOffset));

        g2.drawLine(forkStartX, forkStartY, handlebarX, handlebarY);
    }

    private void drawWheel(Graphics2D g2, int cx, int cy) {
        int outerR = s(96);   
        int innerR = s(80);    
        int hubR = s(20);      

        g2.setColor(new Color(130, 130, 130));
        g2.fillOval(cx - outerR, cy - outerR, outerR * 2, outerR * 2);

        g2.setColor(Color.WHITE);
        g2.fillOval(cx - innerR, cy - innerR, innerR * 2, innerR * 2);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(sw(3f)));
        g2.drawOval(cx - outerR, cy - outerR, outerR * 2, outerR * 2);
        g2.drawOval(cx - innerR, cy - innerR, innerR * 2, innerR * 2);

        g2.setStroke(new BasicStroke(sw(2f)));
        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(i * 15); 
            int x1 = (int) Math.round(cx + Math.cos(angle) * (innerR - 1));
            int y1 = (int) Math.round(cy + Math.sin(angle) * (innerR - 1));
            int x2 = (int) Math.round(cx + Math.cos(angle) * hubR);
            int y2 = (int) Math.round(cy + Math.sin(angle) * hubR);
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }

        g2.setColor(new Color(85, 85, 85));
        g2.fill(new Ellipse2D.Double(cx - hubR, cy - hubR, hubR * 2, hubR * 2));

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(sw(2f)));
        g2.draw(new Ellipse2D.Double(cx - hubR, cy - hubR, hubR * 2, hubR * 2));
    }

    private int tx(int relX) {
        return x + s(relX);
    }

    private int ty(int relY) {
        return y + s(relY);
    }

    private int s(int value) {
        return (int) Math.round(value * scale);
    }

    private int sw(float value) {
        return Math.max(1, Math.round(value * (float) scale));
    }
}