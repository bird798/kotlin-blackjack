package blackjack.core.player

import blackjack.core.amount.Amount
import blackjack.core.amount.BettingAmount

class Dealer : Player(Name(DEALER_NAME), BettingAmount(0)) {
    fun checkMatch(player: Player): MatchResult {
        return when {
            this.checkBust() -> MatchResult.LOSE
            player.checkBust() -> MatchResult.WIN
            this.point > player.point -> MatchResult.WIN
            this.point < player.point -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }

    fun calculateProfit(players: Players) {
        players.play { player -> calculateProfit(player) }
    }

    private fun calculateProfit(
        player: Player,
    ) {
        when (checkMatch(player)) {
            MatchResult.WIN -> moveAmount(player, this, player.bettingAmount)
            MatchResult.LOSE -> moveAmount(this, player, getProfitAmount(player))
            MatchResult.DRAW -> {}
        }
    }

    private fun moveAmount(
        from: Player,
        to: Player,
        amount: Amount,
    ) {
        from.profitAmount -= amount
        to.profitAmount += amount
    }

    private fun getProfitAmount(player: Player): Amount {
        return if (player.checkBlackJack()) {
            player.bettingAmount * BLACKJACK_BONUS_WEIGHT
        } else {
            player.bettingAmount
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val BLACKJACK_BONUS_WEIGHT = 1.5f
    }
}
