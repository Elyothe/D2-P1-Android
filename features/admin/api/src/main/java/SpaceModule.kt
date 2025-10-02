import com.example.d2_p1.admin.data.SpaceRepositoryImplementation
import com.example.d2_p1.admin.domain.spaceRepository
import org.koin.dsl.module

val SpaceModule = module {
    single<spaceRepository> { SpaceRepositoryImplementation() }
}