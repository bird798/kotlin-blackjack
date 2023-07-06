package blackjack

import blackjack.domain.Dealer
import blackjack.domain.FightResult
import blackjack.domain.Player
import blackjack.service.FightService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FightServiceTest {
    @Test
    fun `딜러와 플레이어의 승패를 계산할 수 있다`() {
        val player = Player("사람1")
        val dealer = Dealer()

        val fightService = FightService()
        assertThat(fightService.go(dealer, player)).isInstanceOf(FightResult::class.java)
    }
}
