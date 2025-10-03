import com.example.d2_p1.admin.data.SpaceRepositoryImplementation
import com.example.d2_p1.admin.domain.SpaceRepository
import org.koin.dsl.module

val SpaceModule = module {
    single<SpaceRepository> { SpaceRepositoryImplementation() }
}