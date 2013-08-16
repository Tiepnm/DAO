package qsoft


import grails.plugins.selenium.SeleniumAware
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

import tictactoe.GameController

/**
 * Created with IntelliJ IDEA.
 * User: tiep
 * Date: 8/15/13
 * Time: 6:18 AM
 * To change this template use File | Settings | File Templates.
 */
@Mixin(SeleniumAware)
class GameRunnerTest extends GroovyTestCase
{

    @Before
    void setUp()
    {
    }

    @After
    void tearDown()
    {
        super.tearDown()
    }

    void startGame()
    {
        selenium.open("http://localhost:8080/Tictactoe/game/startGame?player=X")
    }

    void stopGame()
    {
        selenium.open("http://localhost:8080/Tictactoe/game/stopGame")
    }

    void move(int x, int y)
    {

        selenium.open("http://localhost:8080/Tictactoe/game/move?player=X&x=" + x + "&y=" + y)
    }

    void gameHasStarted()
    {
        JSONObject result = new JSONObject(selenium.getBodyText());
        assertEquals(200, result.get("code"))
        assertEquals("GAME START", result.get("message"))
        assertEquals("X", result.get("player"))
    }

    void gameHasStopped()
    {
        JSONObject result = new JSONObject(selenium.getBodyText());
        assertEquals(200, result.get("code"))
        assertEquals("GAME STOP", result.get("message"))
    }

    void hasMoveTo(int x, int y)
    {
        JSONObject result = new JSONObject(selenium.getBodyText());
        assertEquals(200, result.get("code"))
        assertEquals(x, result.get("x"))
        assertEquals(y, result.get("y"))
    }

    @Test
    void testUserJoinGameAndStopGame()
    {
        startGame()
        gameHasStarted()
        stopGame()
        gameHasStopped()

    }

    @Test
    void testUserJoinGameAndMoveAndStopGame()
    {
        startGame()
        move(0, 0)
        hasMoveTo(0, 0)
        stopGame()
        gameHasStopped()
    }
}
