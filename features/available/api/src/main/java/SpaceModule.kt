import com.example.d2_p1.available.data.SpaceRepositoryImplementation
import com.example.d2_p1.available.domain.SpaceRepository
import org.koin.dsl.module

val SpaceModule = module {
    single<SpaceRepository> { SpaceRepositoryImplementation() }
}