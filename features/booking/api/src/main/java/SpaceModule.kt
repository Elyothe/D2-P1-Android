import com.example.d2_p1.booking.data.SpaceRepositoryImplementation
import com.example.d2_p1.booking.domain.SpaceRepository
import org.koin.dsl.module

val SpaceModule = module {
    single<SpaceRepository> { SpaceRepositoryImplementation() }
}