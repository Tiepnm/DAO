package tictactoe

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject
/**
 * Created with IntelliJ IDEA.
 * User: tiep
 * Date: 8/15/13
 * Time: 6:28 AM
 * To change this template use File | Settings | File Templates.
 */
class GameController
{

    def index = {}

    def startGame = {

        render([code: 200, message: 'GAME START', player: params.player] as JSON)

    }
    def stopGame = {
        render([code: 200, message: 'GAME STOP'] as JSON)
    }
    def move = {
        render([code: 200, x: Integer.parseInt(params.x), y: Integer.parseInt(params.y)] as JSON)
    }
}
